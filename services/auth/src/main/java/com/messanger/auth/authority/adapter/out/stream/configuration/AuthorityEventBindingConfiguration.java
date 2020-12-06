package com.messanger.auth.authority.adapter.out.stream.configuration;

import com.messanger.auth.authority.adapter.out.stream.binding.AuthorityEventBinding;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(AuthorityEventBinding.class)
public class AuthorityEventBindingConfiguration {
}
