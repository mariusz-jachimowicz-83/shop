(ns shop.boundaries.basket
  (:require
   [duct.logger    :refer [log]]
   [integrant.core :as ig]))

(def state (atom []))

(defprotocol BasketService
  (details [this])
  (add     [this game])
  (reject  [this game-name])
  (pay     [this]))

(defrecord BasketBoundary [host key logger]
  BasketService
  (details [_]
    (clojure.pprint/pprint @state)
    @state)
  (add [_ game]
    (swap! state conj game))
  (reject [_ game-name]
    (->> @state
         (remove #(= game-name (:name %)))
         (reset! state)))
  (pay [_]
    (reset! state [])))

(defmethod ig/init-key :shop.boundaries/basket
  [_ conf]
  (map->BasketBoundary conf))

