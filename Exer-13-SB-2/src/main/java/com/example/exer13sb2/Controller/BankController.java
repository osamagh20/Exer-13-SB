package com.example.exer13sb2.Controller;

import com.example.exer13sb2.ApiController.ApiResponse;
import com.example.exer13sb2.Model.Bank;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank")
public class BankController {

    ArrayList<Bank> customers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Bank> getCustomers(){
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Bank customer){
        customers.add(customer);
        return new ApiResponse("customer data  added");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index,@RequestBody Bank customer){
        customers.set(index,customer);
        return new ApiResponse("customer data updated");

    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index){
        customers.remove(index);
        return new ApiResponse("customer data  deleted");

    }

    @PutMapping("/deposit/{index}")
    public ApiResponse deposit(@PathVariable int index,@RequestBody int money){

        for (Bank customer : customers) {
            if (customer.getBalance() >= money) {

                customer.setBalance(customer.getBalance() - money);

            }
        }
        return new ApiResponse("success deposit");
    }

    @PutMapping("/withdrawal/{index}")
    public ApiResponse withdrawal(@PathVariable int index ,@RequestBody int money){

        customers.get(index).setBalance(customers.get(index).getBalance() + money);
        return new ApiResponse("success withdrawal");
       
    }
}
