(ns moebel-computing.accounts)

(def accounts-payable    (atom 0))
(def accounts-receivable (atom 0))

(defn zero-accounts []
  (reset! accounts-payable    0)
  (reset! accounts-receivable 0))

(defn projected-profit []
  (- @accounts-receivable @accounts-payable))

(defn print-account-status []
  (println "Receivable:" @accounts-receivable)
  (println "Payable:   " @accounts-payable)
  (println "Profit:    " (projected-profit)))

(defn register-income   [amount] (swap! accounts-receivable + amount))
(defn register-expenses [amount] (swap! accounts-payable    + amount))

(defn process-order [income expenses]
  (future
    (register-expenses (expenses))
    (register-income   (income))
    (projected-profit)))


