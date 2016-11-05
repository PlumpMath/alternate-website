(ns site.core
  (:require [enfocus.core :as ef]
            [enfocus.events :as events]
            [enfocus.effects :as effects]
            [site.actions :as actions])

 (:require-macros [enfocus.macros :as em]))

(def width (.-innerWidth js/window))
(def width-threshold 400)

(def heading [[:div
               {:class "h2"}
               "Hi, I'm Mike!"]
             [:div
               {:class "h2"}
                "Test!"]
             [:div
               {:class "contact"}
               "Contact info!"]])

(def content [[:div
              {:class "h2"}
              "Some content"]
              [:div
                {:class "h2"}
                 "More content!"]
              [:div
                {:class "h2"}
                "Awesome content!"]
              [:div
                {:class "h2"}
                "Awesome content!"]])

(defn start []
    ;;draw our main document
    (ef/at ["body"]
           (ef/do->
            (ef/content
               (ef/html [:div {:id "heading" :class "row"}])
               (ef/html [:div {:id "content" :class "content"}])))
           ["#heading"]
              (ef/content
                  (map #(ef/html
                    [:div {:class "row-el"
                           :id "resizeheading"} %]) heading))
           ["#content"]
               (ef/content
                   (map #(ef/html
                     [:div {:class "row-el"
                            :id "resizecontent"} %]) content))
           ["* #resizeheading"]
               (ef/set-style :width
                             (str (actions/get-width-setting (count heading)
                                                                width
                                                                width-threshold)
                                                                "px"))
           ["* #resizecontent"]
               (ef/set-style :width
                             (str (actions/get-width-setting (count content)
                                                                width
                                                                width-threshold))
                                                                "px"))

    ;;allows dynamic resizing -- no flex-box!
    (ef/at js/window
        (events/listen :resize #(do (actions/squeeze "* #resizeheading"
                                                     width-threshold
                                                     (.-innerWidth js/window)
                                                     (count heading))
                                    (actions/squeeze "* #resizecontent"
                                                     width-threshold
                                                     (.-innerWidth js/window)
                                                     (count content))))))

(set! (.-onload js/window) start)
