(defproject handlers "0.1.0-SNAPSHOT"
  :description "Handlers for nginx-clojure web framework tests"
  :url "http://example.com/FIXME"
  :dependencies [
                 ;[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojure "1.5.1"] ; maybe match nginx version if there are issues
                 [clj-json "0.5.3" ]
                 ;[org.clojure/java.jdbc "0.3.3"]
                 [mysql/mysql-connector-java "5.1.6"]

                 ; bitch try this

                 ;[compojure "1.1.5"]
                 ;[ring/ring-json "0.2.0"]
                 ;[org.clojure/tools.cli "0.2.1"]
                 [http-kit/dbcp "0.1.0"]
                 ;[http-kit "2.0.1"]
                 ;[log4j "1.2.15" :exclusions [javax.mail/mail javax.jms/jms com.sun.jdmk/jmxtools com.sun.jmx/jmxri]]


                 ]

  :main ^:skip-aot handlers.core ; ??
  :target-path "target/%s"
  :uberjar-name "handlers-standalone.jar"
  :profiles {:uberjar {:aot :all}})
