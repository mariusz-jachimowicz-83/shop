(ns shop.views.template
  (:require
   [hiccup.page            :refer [html5 include-css include-js]]
   [hiccup.element         :refer [link-to]]
   [hiccup.form            :as form]
   [ring.util.anti-forgery :refer [anti-forgery-field]]))

(def css
  "main {
    margin-top: 56px;
  }")

(defn search-form
  []
  [:form.form-inline.mt-2.mt-md-0
   {:action "/search"
    :method "post"}
   (anti-forgery-field)
   [:input.form-control.mr-sm-2
    {:type "text", :aria-label "Search", :placeholder "Search"}]
   [:button.btn.btn-outline-success.my-2.my-sm-0
    {:type "submit"}
    "Search"]])

(defn cart-link
  [items-count]
  [:a.btn.btn-success.ml-3
   {:href "checkout"}
   [:i.fa.fa-shopping-cart]
   " Cart\n                    "
   (when (pos? items-count)
     [:span.badge.badge-light items-count])])

(defn page
  [basket-items content]
  (html5
    [:head
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1, shrink-to-fit=no"}]
     [:title "Games"]
     (include-css "https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css")
     (include-js
       "https://code.jquery.com/jquery-3.3.1.slim.min.js"
       "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
       "https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js")
     [:style css]
     [:body
      [:header
       [:nav.navbar.navbar-expand-md.navbar-dark.fixed-top.bg-dark
        [:a.navbar-brand {:href "/"} "Game Rent"]
        [:button.navbar-toggler
         {:aria-label "Toggle navigation",
          :aria-expanded "false",
          :aria-controls "navbarCollapse",
          :data-target "#navbarCollapse",
          :data-toggle "collapse",
          :type "button"}
         [:span.navbar-toggler-icon]]
        [:div#navbarCollapse.collapse.navbar-collapse
         (search-form)
         (cart-link (count basket-items))]]]
      [:main
       {:role "main"}
       content]]]))

(defn labeled-radio [group]
  (fn [checked? label]
    [:div.form-check.col
     (form/radio-button {:class "form-check-input"} group checked? label)
     (form/label {:class "form-check-label"} (str "label-" label) (str label))]))
