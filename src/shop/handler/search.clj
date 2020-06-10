(ns shop.handler.search
  (:require
   [ataraxy.core           :as ataraxy]
   [ataraxy.response       :as response]
   [shop.views.index       :as views.index]
   [integrant.core         :as ig]
   [shop.boundaries.api    :as iapi]
   [shop.boundaries.basket :as ibasket]))

(defmethod ig/init-key :shop.handler/search
  [_ {:keys [api basket] :as options}]
  (fn [{[_ search-form query-params] :ataraxy/result :as request}]
    [::response/ok (views.index/games (iapi/search-games api (get query-params "title"))
                                      (ibasket/details basket))]))
