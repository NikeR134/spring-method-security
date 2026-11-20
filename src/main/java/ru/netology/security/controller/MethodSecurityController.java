package ru.netology.security.controller;

import javax.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MethodSecurityController {
    @GetMapping("/methods/read")
    @Secured("ROLE_READ")
    public String read() {
        return "Read access granted";
    }

    @GetMapping("/methods/write")
    @RolesAllowed("WRITE")
    public String write() {
        return "Write access granted";
    }

    @GetMapping("/methods/write-or-delete")
    @PreAuthorize("hasAnyRole('WRITE', 'DELETE')")
    public String writeOrDelete() {
        return "Write or delete access granted";
    }

    @GetMapping("/methods/users")
    @PreAuthorize("#username == authentication.name")
    public String user(@RequestParam String username) {
        return username;
    }
}

