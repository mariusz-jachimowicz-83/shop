(ns shop.views.thankyou
  (:require
   [shop.views.template :refer [page]]))

(defn congratulations
  []
  (page
   []
   [:div.album.py-5.bg-light
    [:div.container.mx-auto.text-center
     [:h2.display-4 "Thank You"]
     [:p.lead "You succesfully rent games."]]]))
