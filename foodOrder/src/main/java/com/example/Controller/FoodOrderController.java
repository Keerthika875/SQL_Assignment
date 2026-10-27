
package com.example.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Model.FoodOrder;
import com.example.Service.FoodOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class FoodOrderController {

    @Autowired
    private FoodOrderService service;

    // GET: Get all orders
    @GetMapping
    public List<FoodOrder> getAllOrders() {
        return service.getAllOrders();
    }

    // GET: Get order by ID
    @GetMapping("/{id}")
    public FoodOrder getOrderById(@PathVariable Long id) {
        return service.getOrderById(id);
    }

    // POST: Add order
    @PostMapping
    public FoodOrder addOrder(@Valid @RequestBody FoodOrder order) {
        return service.addOrder(order);
    }

    // PUT: Update order
    @PutMapping("/{id}")
    public FoodOrder updateOrder(
            @PathVariable Long id,
            @Valid @RequestBody FoodOrder order) {
        return service.updateOrder(id, order);
    }

    // PATCH: Partial update
    @PatchMapping("/{id}")
    public FoodOrder patchOrder(
            @PathVariable Long id,
            @RequestBody FoodOrder order) {
        return service.patchOrder(id, order);
    }

    // DELETE: Delete order
    @DeleteMapping("/{id}")
    public Map<String, String> deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);

        return Map.of("message", "Order deleted successfully");
    }

    // GET: Count orders
    @GetMapping("/count")
    public Map<String, Integer> getOrderCount() {
        return Map.of("count", service.getOrderCount());
    }
}
