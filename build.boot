(set-env!
  ;; using the sonatype repo is sometimes useful when testing Clojurescript
  ;; versions that not yet propagated to Clojars
  ;; :repositories #(conj % '["sonatype" {:url "http://oss.sonatype.org/content/repositories/releases"}])
  :dependencies '[[org.clojure/clojure            "1.8.0"]
                  [org.clojure/clojurescript      "1.9.293"]
                  [adzerk/boot-reload             "0.4.13"]
                  [tailrecursion/boot-jetty       "0.1.3"]
                  [adzerk/boot-cljs               "1.7.48-5"]
                  [com.cemerick/piggieback        "0.2.1"  :scope "test"]
                  [weasel                         "0.7.0"  :scope "test"]
                  [enfocus                        "2.1.1"]]

  :source-paths  #{"src"}
  :asset-paths   #{"assets"})

  (require
    '[adzerk.boot-cljs              :refer [cljs]]
    '[adzerk.boot-reload            :refer [reload]]
    '[tailrecursion.boot-jetty      :refer [serve]])

(deftask dev []
  (comp
    (watch)
    (cljs :optimizations :none)
    (reload)
    (serve :port 8000)))
