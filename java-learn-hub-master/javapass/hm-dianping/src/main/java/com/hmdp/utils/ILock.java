package com.hmdp.utils;

public interface ILock {

    public boolean tryLock(Long timeoutSec);
    public void unlock();

}
