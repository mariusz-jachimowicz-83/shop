(ns shop.boundaries.api
  (:require
   [duct.logger        :refer [log]]
   [cheshire.core      :as cheshire]
   [integrant.core     :as ig]
   [org.httpkit.client :as http]))

(def default-header
  {"Content-Type" "application/json"})

(defprotocol ApiService
  (service-url   [this])
  (all-games     [this])
  (search-games  [this game-name]))

(defn- response->games-with-price
  [response]
  (map #(assoc % :price 10)
       (-> response
           :body
           (cheshire/decode true)
           :results)))

(defrecord ApiBoundary [host key logger]
  ApiService
  (service-url [_]
    host)
  (all-games [_]
    (let [response @(http/get (format "%s/games?api_key=%s&format=json&limit=20"
                                      host
                                      key)
                              {:headers default-header})]
      (if (= 200 (:status response))
        (response->games-with-price response)
        [])))
  (search-games [_ game-name]
    (let [response @(http/get (format "%s/games?api_key=%s&format=json&limit=20&filter=name:%s"
                                      host
                                      key
                                      game-name)
                              {:headers default-header})]
      (if (= 200 (:status response))
        (response->games-with-price response)
        []))))

(defmethod ig/init-key :shop.boundaries/api
  [_ conf]
  (map->ApiBoundary conf))

