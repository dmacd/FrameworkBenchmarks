(defproject handlers "0.1.0-SNAPSHOT"
  :description "Handlers for nginx-clojure web framework tests"
  :url "http://example.com/FIXME"
  :dependencies [
                 [org.clojure/clojure "1.6.0"]
                 ;[org.clojure/clojure "1.5.1"] ; maybe match nginx version if there are issues
                 [clj-json "0.5.3" ]]
  :main ^:skip-aot handlers.core ; ??
  :target-path "target/%s"
  :uberjar-name "handlers-standalone.jar"
  :profiles {:uberjar {:aot :all}})
