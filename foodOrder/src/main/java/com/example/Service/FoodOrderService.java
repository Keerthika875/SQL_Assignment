
package com.example.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Exception.OrderNotFoundException;
import com.example.Model.FoodOrder;

@Service
public class FoodOrderService {

    private final ArrayList<FoodOrder> orders = new ArrayList<>();

    private long nextId = 1;

    // Create a copy of an order
    private FoodOrder copyOrder(FoodOrder order) {
        return new FoodOrder(
            order.getOrderId(),
            order.getCustomerName(),
            order.getFoodItem(),
            order.getQuantity(),
            order.getPrice()
        );
    }

    // Add an order
    public FoodOrder addOrder(FoodOrder request) {

        FoodOrder order = new FoodOrder(
            nextId++,
            request.getCustomerName().trim(),
            request.getFoodItem().trim(),
            request.getQuantity(),
            request.getPrice()
        );

        orders.add(order);

        return copyOrder(order);
    }

    // Get all orders
    public List<FoodOrder> getAllOrders() {

        return orders.stream()
                .map(this::copyOrder)
                .toList();
    }

    // Get an order by ID
    public FoodOrder getOrderById(Long id) {

        FoodOrder order = orders.stream()
                .filter(item -> item.getOrderId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                    new OrderNotFoundException(
                        "Order not found with ID: " + id
                    )
                );

        return copyOrder(order);
    }

    // Update an order
    public FoodOrder updateOrder(Long id, FoodOrder request) {

        FoodOrder existingOrder = orders.stream()
                .filter(item -> item.getOrderId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                    new OrderNotFoundException(
                        "Order not found with ID: " + id
                    )
                );

        existingOrder.setCustomerName(
            request.getCustomerName().trim()
        );
        existingOrder.setFoodItem(
            request.getFoodItem().trim()
        );
        existingOrder.setQuantity(request.getQuantity());
        existingOrder.setPrice(request.getPrice());

        return copyOrder(existingOrder);
    }

    // Delete an order
    public void deleteOrder(Long id) {

        FoodOrder order = orders.stream()
                .filter(item -> item.getOrderId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                    new OrderNotFoundException(
                        "Order not found with ID: " + id
                    )
                );

        orders.remove(order);
    }

    // Get total order count
    public int getOrderCount() {
        return orders.size();
    }

    // Partial update (PATCH)
    public FoodOrder patchOrder(Long id, FoodOrder request) {

        FoodOrder existingOrder = orders.stream()
                .filter(item -> item.getOrderId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                    new OrderNotFoundException(
                        "Order not found with ID: " + id
                    )
                );

        if (request.getCustomerName() != null) {
            if (request.getCustomerName().isBlank()) {
                throw new IllegalArgumentException(
                    "Customer name cannot be blank"
                );
            }
            existingOrder.setCustomerName(
                request.getCustomerName().trim()
            );
        }

        if (request.getFoodItem() != null) {
            if (request.getFoodItem().isBlank()) {
                throw new IllegalArgumentException(
                    "Food item cannot be empty"
                );
            }
            existingOrder.setFoodItem(
                request.getFoodItem().trim()
            );
        }

        if (request.getQuantity() != 0) {
            if (request.getQuantity() < 1) {
                throw new IllegalArgumentException(
                    "Quantity must be at least 1"
                );
            }
            existingOrder.setQuantity(request.getQuantity());
        }

        if (request.getPrice() != null) {
            if (request.getPrice().signum() <= 0) {
                throw new IllegalArgumentException(
                    "Price must be greater than 0"
                );
            }
            existingOrder.setPrice(request.getPrice());
        }

        return copyOrder(existingOrder);
    }
}
