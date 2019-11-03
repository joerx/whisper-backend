package io.yodo.whisper.service;

public interface ClientCredentialService {

    boolean isValidClient(String clientId, String clientSecret);
}
