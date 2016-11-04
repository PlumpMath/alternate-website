(ns site.styles
  (:require [forest.macros :refer-macros [defstylesheet]]
            [forest.class-names :refer [class-names]]
            [enfocus.core :as ef]
            [enfocus.events :as events]
            [enfocus.effects :as effects])

  (:require-macros [enfocus.macros :as em]))

(defn even-width [w n]
  "given width w(px), return a width(%) evenly divded by n"
  (str (/ w n) "px"))

(em/defaction squeeze [selector squeeze-point current-width]
  [selector]
   (if (> current-width squeeze-point)
        (ef/set-style :width
         (even-width current-width 3))
        (ef/set-style :width "100%")))

(defn construct-stylesheet [width]
  (defstylesheet site
    [.html {:box-sizing "border-box"}]
    [.row {:width "100%"
           :border-color "black"
           :font-family "Andale Mono"
           :display "block"
           :float "left"
           :box-sizing "inherit"
           :min-height "120px"
           :background "rgb(255,80,80)"}]
    [.row-el {:composes row
              :width width
              :text-align "left"}]
    [.h2     {:padding "10px"
              :border-width "2px"
              :height "119px"
              :border-style "dashed"}]
    [.contact{:composes h2
              :text-align "right"}]))
