(ns shop.handler.basket
  (:require
   [ataraxy.core           :as ataraxy]
   [ataraxy.response       :as response]
   [shop.views.basket      :as views.basket]
   [integrant.core         :as ig]
   [shop.boundaries.basket :as ibasket]))

(defmethod ig/init-key :shop.handler/basket
  [_ {:keys [api basket] :as options}]
  (fn [{[_] :ataraxy/result}]
    [::response/ok (views.basket/form (ibasket/details basket))]))
