(ns cider.nrepl
  (:require [clojure.tools.nrepl.server :as nrepl-server]
            [cider.nrepl.middleware.classpath]
            [cider.nrepl.middleware.complete]
            [cider.nrepl.middleware.info]
            [cider.nrepl.middleware.inspect]
            [cider.nrepl.middleware.macroexpand]
            [cider.nrepl.middleware.resource]
            [cider.nrepl.middleware.stacktrace]
            [cider.nrepl.middleware.test]
            [cider.nrepl.middleware.trace]))

(def cider-middleware
  "A vector containing all CIDER middleware."
  '[cider.nrepl.middleware.classpath/wrap-classpath
    cider.nrepl.middleware.complete/wrap-complete
    cider.nrepl.middleware.info/wrap-info
    cider.nrepl.middleware.inspect/wrap-inspect
    cider.nrepl.middleware.macroexpand/wrap-macroexpand
    cider.nrepl.middleware.resource/wrap-resource
    cider.nrepl.middleware.stacktrace/wrap-stacktrace
    cider.nrepl.middleware.test/wrap-test
    cider.nrepl.middleware.trace/wrap-trace])

(defn cider-nrepl-handler []
  (apply nrepl-server/default-handler (map resolve cider-middleware)))

(def version
  "Current version of CIDER nREPL, map of :major, :minor, :incremental, and :qualifier."
  (let [version-string "0.7.0-snapshot"]
    (assoc (->> version-string
                (re-find #"(\d+)\.(\d+)\.(\d+)-?(.*)")
                rest
                (zipmap [:major :minor :incremental :qualifier]))
      :version-string version-string)))
