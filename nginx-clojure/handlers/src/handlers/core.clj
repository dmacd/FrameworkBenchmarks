(ns handlers.core
  (:gen-class)
  (:require ;clojure.data.json
		clj-json.core))


;;; convert to int
(defn to-int [s] (cond
                  (string? s) (Integer/parseInt s)
                  (instance? Integer s) s
                  (instance? Long s) (.intValue ^Long s)
                  :else 0))

;; Query a random World record from the database
(defn get-world []
  (let [id (inc (rand-int 9999))] ; Num between 1 and 10,000
    ; Set a naming strategy to preserve column name case
    (clojure.java.jdbc/with-naming-strategy {:keyword identity}
      (db/query "select * from world where id = ?" id))))

;; Run the specified number of queries, return the results
(defn run-queries [queries]
   (flatten ; Make it a list of maps
    (take
     queries ; Number of queries to run
     (repeatedly get-world))))

(defn get-query-count [queries]
  "Parse provided string value of query count, clamping values to between 1 and 500."
  (let [q (try (Integer/parseInt queries)
               (catch Exception e 1))] ; default to 1 on parse failure
    (if (> q 500)
      500 ; clamp to 500 max
      (if (< q 1)
        1 ; clamp to 1 min
        q)))) ; otherwise use provided value



(defn handle-json [req]
  {
   :status 200,
   :headers {"content-type" "application/json"},
   ;		:body "{\\"message\\":\\"Hello, World!\\"}"
   ;		:body   (clojure.data.json/write-str  { \"message\" \"Hello, World!\"})
   :body   (clj-json.core/generate-string  { "message" "Hello, World!"})
   })


(defn handle-queries [req]
  {
   :status   200,
   :headers  {"content-type" "application/json"},
   :body     (clj-json.core/generate-string
              (first (run-queries 1)))

   }

  )




(defn init-server [{:keys [port db-host]}]
  (println "Initializing clojure handler...")
  (println (str "db-host: " db-host) )
  (db/use-database! (str "jdbc:mysql://" db-host "/hello_world?jdbcCompliantTruncation=false&elideSetAutoCommits=true&useLocalSessionState=true&cachePrepStmts=true&cacheCallableStmts=true&alwaysSendSetIsolation=false&prepStmtCacheSize=4096&cacheServerConfiguration=true&prepStmtCacheSqlLimit=2048&zeroDateTimeBehavior=convertToNull&traceProtocol=false&useUnbufferedInput=false&useReadAheadInput=false&maintainTimeStats=false&useServerPrepStmts&cacheRSMetadata=true")
                    "benchmarkdbuser"
                    "benchmarkdbpass")
  )










(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(fn []

  (def +json+ "{\"apple\":\"pie\", \"banana\":{\"qwertyuiopasdfghjklzxcvbnm,\":100, \"askdfj adlskfj\":[1,2,3,4,5,1,2,3,4,1,2,3,4,3,3,2,2,2,2,2,2,2,\"Whoa there!\"]}, \"anarray\":[893172,19203809,12093810928309123,1237732,123,0.111]}")

;  (clojure.data.json/read-str +json+)


;  (clojure.data.json/write-str  { "message" "Hello World"})

;  (clojure.data.json/write-str (clojure.data.json/read-str +json+))


  (clj-json.core/generate-string { "message" "Hello, World!"})


  (- 36 (* 5 (- 36 33.5)))
)
