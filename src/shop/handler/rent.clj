(ns shop.handler.rent
  (:require
   [ataraxy.core           :as ataraxy]
   [ataraxy.response       :as response]
   [integrant.core         :as ig]
   [shop.boundaries.basket :as ibasket]))

(defmethod ig/init-key :shop.handler/rent
  [_ {:keys [basket] :as options}]
  (fn [{[_ rent-form] :ataraxy/result :as request}]
    (ibasket/pay basket)
    [::response/see-other "/thankyou"]))
