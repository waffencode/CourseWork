package com.course.client.service;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

public class HashProvider
{
    public static String getStringHash(String inputString)
    {
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(inputString, Charsets.UTF_8);
        HashCode sha256 = hasher.hash();
        return sha256.toString();
    }
}
