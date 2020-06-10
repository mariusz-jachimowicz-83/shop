(ns shop.views.basket
  (:require
   [ring.util.anti-forgery :refer [anti-forgery-field]]
   [shop.views.template    :refer [page]]))

(defn item
  [game]
  [:li.list-group-item.d-flex.justify-content-between.lh-condensed
   [:div
    [:h6.my-0 (:name game)]
    [:small.text-muted (:deck game)]]
   [:span.text-muted (format "$%s" (:price game))]])

(defn total-price
  [basket-items]
  (->> basket-items (map :price) (reduce + 0)))

(defn form [basket-items]
  (page
   basket-items
   [:div.album.py-5.bg-light
    [:div.container
     [:div.py-5.text-center
      [:h2 "Renting games"]
      [:p.lead "Please verify all items on the card and fill in billing details"]]
     [:div.row
      [:div.col-md-4.order-md-2.mb-4
       [:h4.d-flex.justify-content-between.align-items-center.mb-3
        [:span.text-muted "Your cart"]
        [:span.badge.badge-secondary.badge-pill (count basket-items)]]
       [:ul.list-group.mb-3
        (for [game basket-items]
          (item game))
        [:li.list-group-item.d-flex.justify-content-between
         [:span "Total (USD)"]
         [:strong (format "$%s" (total-price basket-items))]]]]
      [:div.col-md-8.order-md-1
       [:h4.mb-3 "Billing address"]
       [:form.needs-validation
        {
         ;; :novalidate ""
         :action "/rent"
         :method "post"}
        (anti-forgery-field)
        [:div.row
         [:div.col-md-6.mb-3
          [:label {:for "firstName"} "First name"]
          [:input#firstName.form-control
           {:required "", :value "", :placeholder "", :type "text", :name "firstName"}]
          [:div.invalid-feedback
           "\n              Valid first name is required.\n            "]]
         [:div.col-md-6.mb-3
          [:label {:for "lastName"} "Last name"]
          [:input#lastName.form-control
           {:required "", :value "", :placeholder "", :type "text", :name "lastName"}]
          [:div.invalid-feedback
           "\n              Valid last name is required.\n            "]]]
        [:div.mb-3
         [:label
          {:for "email"}
          "Email "
          [:span.text-muted "(Optional)"]]
         [:input#email.form-control
          {:placeholder "you@example.com", :type "email", :name "email"}]
         [:div.invalid-feedback
          "\n            Please enter a valid email address for shipping updates.\n          "]]
        [:div.mb-3
         [:label {:for "address"} "Address"]
         [:input#address.form-control
          {:required "", :placeholder "1234 Main St", :type "text", :name "address"}]
         [:div.invalid-feedback
          "\n            Please enter your shipping address.\n          "]]
        [:div.mb-3
         [:label
          {:for "address2"}
          "Address 2 "
          [:span.text-muted "(Optional)"]]
         [:input#address2.form-control
          {:placeholder "Apartment or suite", :type "text", :name "address2"}]]
        [:div.row
         [:div.col-md-5.mb-3
          [:label {:for "country"} "Country"]
          [:select#country.custom-select.d-block.w-100
           {:required "", :name "country"}
           [:option {:value ""} "Choose..."]
           [:option "United States"]]
          [:div.invalid-feedback
           "\n              Please select a valid country.\n            "]]
         [:div.col-md-4.mb-3
          [:label {:for "state"} "State"]
          [:select#state.custom-select.d-block.w-100
           {:required "", :name "state"}
           [:option {:value ""} "Choose..."]
           [:option "California"]]
          [:div.invalid-feedback
           "\n              Please provide a valid state.\n            "]]
         [:div.col-md-3.mb-3
          [:label {:for "zip"} "Zip"]
          [:input#zip.form-control
           {:required "", :placeholder "", :type "text", :name "zip"}]
          [:div.invalid-feedback
           "\n              Zip code required.\n            "]]]
        [:hr.mb-4]
        [:div.custom-control.custom-checkbox
         [:input#same-address.custom-control-input {:type "checkbox", :name "same-address"}]
         [:label.custom-control-label
          {:for "same-address"}
          "Shipping address is the same as my billing address"]]
        [:div.custom-control.custom-checkbox
         [:input#save-info.custom-control-input {:type "checkbox", :name "save-info"}]
         [:label.custom-control-label
          {:for "save-info"}
          "Save this information for next time"]]
        [:hr.mb-4]
        [:h4.mb-3 "Payment"]
        [:div.d-block.my-3
         [:div.custom-control.custom-radio
          [:input#credit.custom-control-input
           {:required "",
            :checked "",
            :type "radio",
            :name "paymentMethod"}]
          [:label.custom-control-label {:for "credit"} "Credit card"]]
         [:div.custom-control.custom-radio
          [:input#debit.custom-control-input
           {:required "", :type "radio", :name "paymentMethod"}]
          [:label.custom-control-label {:for "debit"} "Debit card"]]
         [:div.custom-control.custom-radio
          [:input#paypal.custom-control-input
           {:required "", :type "radio", :name "paymentMethod"}]
          [:label.custom-control-label {:for "paypal"} "PayPal"]]]
        [:div.row
         [:div.col-md-6.mb-3
          [:label {:for "cc-name"} "Name on card"]
          [:input#cc-name.form-control
           {:required "", :placeholder "", :type "text", :name "cc-name"}]
          [:small.text-muted "Full name as displayed on card"]
          [:div.invalid-feedback
           "\n              Name on card is required\n            "]]
         [:div.col-md-6.mb-3
          [:label {:for "cc-number"} "Credit card number"]
          [:input#cc-number.form-control
           {:required "", :placeholder "", :type "text", :name "cc-number"}]
          [:div.invalid-feedback
           "\n              Credit card number is required\n            "]]]
        [:div.row
         [:div.col-md-3.mb-3
          [:label {:for "cc-expiration"} "Expiration"]
          [:input#cc-expiration.form-control
           {:required "", :placeholder "", :type "text", :name "cc-expiration"}]
          [:div.invalid-feedback
           "\n              Expiration date required\n            "]]
         [:div.col-md-3.mb-3
          [:label {:for "cc-cvv"} "CVV"]
          [:input#cc-cvv.form-control
           {:required "", :placeholder "", :type "text", :name "cc-cvv"}]
          [:div.invalid-feedback
           "\n              Security code required\n            "]]]
        [:hr.mb-4]
        [:button.btn.btn-primary.btn-lg.btn-block
         {:type "submit"}
         "Rent"]]]]]]))

