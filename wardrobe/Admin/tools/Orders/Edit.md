The "Edit Order" page is an interface within the admin panel that allows you to make adjustments to existing orders. It is located on address `http://localhost:8080/admin/edit-order/order_id`.

When you open it, this page shows up:

![Image of add-discount](edit-order-page.png)

Here's what each part of the page is for:

1. **Order Date**: This field shows the date the order was placed. It may be editable in case there is a need to adjust the order date due to processing delays.
    
2. **User ID**: A unique identifier for the customer who placed the order. This is used to link orders to specific customer accounts within the database.
    
3. **Address**: The field to input or edit the shipping address.
    
4. **Total Price**: Displays the current total price of the order. It is automatically updated if you change the order details, such as product quantities or items.
    
5. **Status**: A dropdown menu to change the status of the order. Options include these statuses "Ordered", "Shipped" and "Delivered". 
    
6. **Product Details Section**: This area is where you can add or modify the products in the order. It might include fields for:
    
    - **Quantity**: The number of each item ordered.
    - **Product Number**: The unique identifier for the product.
    - **Name**: The name of the product as it appears on the website.
    - **Price**: The price of each item.
    
    The "Add Product" button suggests you can include additional items in the order, which 
	might be used if a customer contacts you to add something to their purchase.
	
    The "Delete Product" button lets you delete the product on the same row.
    
7. **Save Order**: After making any changes, this button will save the updates you've made to the order.