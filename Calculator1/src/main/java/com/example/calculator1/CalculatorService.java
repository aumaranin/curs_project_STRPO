package com.example.calculator1;

import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorService
{
    @GetMapping("/add/{x}/{y}")
    public Response add(@PathVariable int x, @PathVariable  int y)
    {
        return new Response(x, y, x+y);
    }

    @PostMapping("/sub")
    public Response sub(@RequestBody Request request)
    {
        int x = request.getX();
        int y = request.getY();
        int result = x-y;
        return new Response(x, y, result);
    }
}
