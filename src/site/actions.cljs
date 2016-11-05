(ns site.actions
  (:require [enfocus.core :as ef])
  (:require-macros [enfocus.macros :as em]))

(defn get-width-setting [n width width-threshold]
  "Return either equally divided widths, or each width at 100 percent if width
  is below threshold."
  (if (> width width-threshold)
        (/ width n)
        width))

(em/defaction squeeze [selector width-threshold current-width n]
  [selector]
    (ef/set-style :width (str
                          (Math.round
                            (- (get-width-setting n current-width width-threshold)
                                0.4))
                          "px")))
