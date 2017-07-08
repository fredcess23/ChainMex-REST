package com.cpmx8.chainmexico.service.impl;


import com.chain.api.Account;
import com.chain.api.MockHsm;
import com.chain.exception.ChainException;
import com.chain.http.Client;
import com.chain.signing.HsmSigner;
import com.cpmx8.chainmexico.service.AccountBuilder;
import org.springframework.stereotype.Service;

@Service
public class AccountBuilderImpl implements AccountBuilder {

    Client client;

    AccountBuilderImpl() {
        try {
            client = new Client();
        } catch (ChainException e) {
            e.printStackTrace();
        }
    }

    public Boolean createAccount(String alias) {

        try {
            MockHsm.Key accountKey = MockHsm.Key.create(client);
            HsmSigner.addKey(accountKey, MockHsm.getSignerClient(client));

            new Account.Builder()
                    .setAlias(alias)
                    .addRootXpub(accountKey.xpub)
                    .setQuorum(1)
                    .addTag("type", "checking")
                    .create(client);
            return true;

        } catch (Exception e) {
            return false;
        }

    }
}
