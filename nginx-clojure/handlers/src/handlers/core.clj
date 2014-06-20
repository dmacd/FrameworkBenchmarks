(ns handlers.core
  (:gen-class)
  (:require clojure.data.json clj-json.core))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


(defn handle-json [req]
  {
   :status 200,
   :headers {"content-type" "application/json"},
   ;		:body "{\\"message\\":\\"Hello, World!\\"}"
   ;		:body   (clojure.data.json/write-str  { \"message\" \"Hello, World!\"})
   :body   (clj-json.core/generate-string  { \"message\" \"Hello, World!\"})
   })






(fn []

  (def +json+ "{\"apple\":\"pie\", \"banana\":{\"qwertyuiopasdfghjklzxcvbnm,\":100, \"askdfj adlskfj\":[1,2,3,4,5,1,2,3,4,1,2,3,4,3,3,2,2,2,2,2,2,2,\"Whoa there!\"]}, \"anarray\":[893172,19203809,12093810928309123,1237732,123,0.111]}")

  (clojure.data.json/read-str +json+)


  (clojure.data.json/write-str  { "message" "Hello World"})

  (clojure.data.json/write-str (clojure.data.json/read-str +json+))


  (clj-json.core/generate-string { "message" "Hello, World!"})


  (- 36 (* 5 (- 36 33.5)))
)
