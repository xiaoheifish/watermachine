package com.terabits.service;

import com.terabits.meta.model.TerminalModel;

/**
 * Created by Administrator on 2017/8/17.
 */
public interface CredentialService {
    public void createTerminal(TerminalModel terminalModel);
    public String getTerminalTime(String terminalId);
    public String getLeftTime(String terminalId);
}
