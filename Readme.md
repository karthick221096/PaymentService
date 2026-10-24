# paymentService
Learning to create a payment service using java

### What this does
* Handles payment initiation for orders in the ecommerce store
* Supports multiple payment gateways like Stripe and Razorpay
* Webhook api for payment gateway to send payment status updates
* Reconciliation cron job to sync payment status from payment gateway
* Idempotent payment apis to avoid duplicate payments

### Requiremnts
* Payment Controller to handle payment requests
* Payment Service layer
* Payment Gateway Selector to choose between multiple payment gateways
* Individual payment gateway implementations (Stripe, Razorpay etc.)
* Webhook endpoint for stripe and razorpay callbacks
* Cron job for reconciliation
* Idempotency handling

### How to run
* Make sure mysql is running on port 3306
* Set STRIPE_API_KEY environment variable with your stripe secret key
* Create database paymentservice in mysql
* Run PaymentServiceApplication.java from intellij
* Service starts on port 8000