(ns site.core
  (:require [forest.macros :refer-macros [defstylesheet]]
            [forest.class-names :refer [class-names]]
            [enfocus.core :as ef]
            [enfocus.events :as events]
            [enfocus.effects :as effects]
            [site.styles :as styles])
 (:require-macros [enfocus.macros :as em]))

(def width (.-innerWidth js/window))
(def width-threshold 620)

(styles/construct-stylesheet (if (> width-threshold
                                    width)
                                    "100%"
                                    (styles/even-width width 3)))

(def heading [[:div
               {:class styles/h2}
               "Hi, I'm Mike!"]
             [:div
               {:class styles/h2}
                "Test!"]
             [:div
               {:class styles/contact}
               "Contact info!"]])

(defn start []
  ;;draw our main document
    (ef/at ["body"] (ef/content (ef/html [:div {:id "heading" :class styles/row}]))
           ["#heading"]
                (ef/content
                    (map #(ef/html
                       [:div {:class styles/row-el
                              :id "resize"} %]) heading)))

    ;;allows dynamic resizing -- no flex-box!
    (ef/at js/window
        (events/listen :resize #(styles/squeeze "* #resize" width-threshold
                                         (.-innerWidth js/window)))))

(set! (.-onload js/window) start)
