(ns site.core
  (:require [forest.macros :refer-macros [defstylesheet]]
            [forest.class-names :refer [class-names]]
            [enfocus.core :as ef]
            [enfocus.events :as events]
            [enfocus.effects :as effects])
 (:require-macros [enfocus.macros :as em]))

(defn even-width [w n]
  "given width w(px), return a width(px) evenly divded by n"
  (str (/ (.-innerWidth js/window) n) "px"))

(defstylesheet site
  [.basic {:margin "0px"
            :font-family "Andale Mono"
            :float "left"
            :background "rgb(100,100,100)"
            :min-height "100px"
            :width "100%"
            :display "block"}]
  [.sub-row {:composes row-el
            :border-width "2px"
            :border-style "dashed"
            :border-color "black"
            :min-height "90px"
            :margin "2px"
            :text-align "center"
            :background "rgb(255,100,100)"}])

(defn draw-even-cels [prefix pad & strings]
  (let [even-pixel-width (even-width (Math.round (.-innerWidth js/window))
                                     (count strings))]

  (defstylesheet flex-elements
    [.row-el {:composes basic
              :width even-pixel-width}])

  (map #(ef/html [:div {:class row-el
                        :id prefix} %]) strings)))

(defn start []
  ;;draw our main document
 (ef/at js/document ["body"]
  (ef/content (draw-even-cels "top" 0
                              [:div [:div {:class sub-row} "something!"]]
                              [:div [:div {:class sub-row} "Test!"]]
                              [:div [:div {:class sub-row} "Another test!"]])))

  ;;allows dynamic resizing -- I don't like flex-box
 (ef/at js/window
        (events/listen :resize #(ef/at js/document
                                      ["* #top"]
                                       (ef/set-style :width
                                        (even-width
                                         (.-innerWidth js/window) 3))))))

(set! (.-onload js/window) start)
