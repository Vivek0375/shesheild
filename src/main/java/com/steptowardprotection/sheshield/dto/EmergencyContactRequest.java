package com.steptowardprotection.sheshield.dto;

public class EmergencyContactRequest {
    private String name;
    private String phone;
    private String email;
    private Long userId;

    // optional nested user object (so API accepts {"user": {"id": 8}})
    private SimpleUserRef user;

    public static class SimpleUserRef {
        private Long id;
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
    }

    public EmergencyContactRequest() {}

    // getters / setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public SimpleUserRef getUser() { return user; }
    public void setUser(SimpleUserRef user) { this.user = user; }
}
