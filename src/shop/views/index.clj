(ns shop.views.index
  (:require
   [ring.util.anti-forgery :refer [anti-forgery-field]]
   [shop.views.template    :refer [page]]))

(defn card-img
  [game]
  [:img.card-img-top
   {:data-holder-rendered "true"
    :src (-> game :image :small_url)
    :style "height: 225px; width: 100%; display: block;"}])

(defn card-title
  [game]
  [:h5.card-title (:name game)])

(defn card-description
  [game]
  [:p.card-text (:deck game)])

(defn btn-rent
  [game]
  [:form
   {:action "/game-add"
    :method "post"}
   (anti-forgery-field)
   [:input {:name "name",  :type "hidden", :value (:name game)}]
   [:input {:name "deck",  :type "hidden", :value (:deck game)}]
   [:input {:name "price", :type "hidden", :value (:price game)}]
   [:button.btn.btn-sm.btn-outline-primary
    {:type "submit"}
    "Rent"]])

(defn btn-details
  []
  [:button.btn.btn-sm.btn-outline-secondary
   {:type "button"}
   "Details"])

(defn games [games basket-items]
  (page
   basket-items
   [:div.album.py-5.bg-light
    [:div.container
    [:div.row
     (for [g games]
[:div.col-md-4
      [:div.card.mb-4.box-shadow
       (card-img g)
       [:div.card-body
        (card-title g)
        (card-description g)
        [:div.d-flex.justify-content-between.align-items-center
         (btn-rent g)
         #_[:div.btn-group
          (btn-rent)
          (btn-details)]
         [:small.text-muted (format "$%s" (:price g))]]]]])]]]))
