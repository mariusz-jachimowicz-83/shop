(ns shop.handler.index
  (:require
   [ataraxy.core           :as ataraxy]
   [ataraxy.response       :as response]
   [shop.views.index       :as views.index]
   [integrant.core         :as ig]
   [shop.boundaries.api    :as iapi]
   [shop.boundaries.basket :as ibasket]))

(defmethod ig/init-key :shop.handler/index
  [_ {:keys [api basket] :as options}]
  (fn [{[_] :ataraxy/result}]
    [::response/ok (views.index/games (iapi/all-games api)
                                      (ibasket/details basket))]))
