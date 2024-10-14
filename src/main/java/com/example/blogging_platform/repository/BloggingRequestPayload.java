package com.example.blogging_platform.repository;

import java.util.List;

public record BloggingRequestPayload(String title, String content, String category, List<String> tags, String createdAt, String updatedAt ) {
}
