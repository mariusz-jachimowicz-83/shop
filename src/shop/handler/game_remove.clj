(ns shop.handler.game-remove
  (:require
   [ataraxy.core           :as ataraxy]
   [ataraxy.response       :as response]
   [integrant.core         :as ig]
   [shop.boundaries.basket :as ibasket]))

(defmethod ig/init-key :shop.handler/game-remove
  [_ {:keys [basket] :as options}]
  (fn [{[_ game-form] :ataraxy/result :as request}]
    (ibasket/reject basket (get game-form "name"))
    [::response/see-other "/checkout"]))
