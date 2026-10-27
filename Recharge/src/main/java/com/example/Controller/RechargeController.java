package com.example.Controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.Model.Recharge;
import com.example.Service.RechargeService;

@RestController
@RequestMapping("/recharges")
public class RechargeController {

    private final RechargeService rechargeService;

    public RechargeController(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    // Add recharge
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recharge addRecharge(@Valid @RequestBody Recharge recharge) {
        return rechargeService.addRecharge(recharge);
    }

    // Get all recharges
    @GetMapping
    public List<Recharge> getAllRecharges() {
        return rechargeService.getAllRecharges();
    }

    // Search by ID
    @GetMapping("/{id}")
    public Recharge getRechargeById(@PathVariable Long id) {
        return rechargeService.getRechargeById(id);
    }

    // Update recharge
    @PutMapping("/{id}")
    public Recharge updateRecharge(
            @PathVariable Long id,
            @Valid @RequestBody Recharge recharge) {
        return rechargeService.updateRecharge(id, recharge);
    }

    // Delete recharge
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecharge(@PathVariable Long id) {
        rechargeService.deleteRecharge(id);
    }

    // Count recharges
    @GetMapping("/count")
    public int getRechargeCount() {
        return rechargeService.getRechargeCount();
    }
}