(ns shop.handler.game-add
  (:require
   [ataraxy.core           :as ataraxy]
   [ataraxy.response       :as response]
   [integrant.core         :as ig]
   [shop.boundaries.basket :as ibasket]))

(defmethod ig/init-key :shop.handler/game-add
  [_ {:keys [basket] :as options}]
  (fn [{[_ game-form] :ataraxy/result :as request}]
    (ibasket/add basket {:name  (get game-form "name")
                         :deck  (get game-form "deck")
                         :price (Integer/parseInt (get game-form "price"))})
    [::response/see-other "/"]))
