(ns shop.handler.thankyou
  (:require
   [ataraxy.core        :as ataraxy]
   [ataraxy.response    :as response]
   [shop.views.thankyou :as views.thankyou]
   [integrant.core      :as ig]))

(defmethod ig/init-key :shop.handler/thankyou
  [_ {:keys [api basket] :as options}]
  (fn [{[_ search-form query-params] :ataraxy/result :as request}]
    [::response/ok (views.thankyou/congratulations)]))
