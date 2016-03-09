package com.example;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
public class DemoApplicationTests {

	@InjectMocks
	AccountService accountService;
	@Mock
	AuthorizationService authorizationService;

	@Mock
	Account account;

	@Captor
	ArgumentCaptor<Integer> amoutCaptor;
	@Before
	public void Setup(){
		//accountService = new AccountService(accountService1 -> true);
		when(authorizationService.isAllowed(any())).thenReturn(true);
		//accountService = new AccountService(authorizationService);

		//Mock optimisation remove : accountService = new AccountService(authorizationService); when @InjectMocks

	}

	@Test
	public void balance_additionned() {
		//Mock optimisation when (account.getBalance()).thenReturn(10);
		Account account = Account.builder().balance(70).build();
		accountService.updateMoney(account,10);
		assertThat(account.getBalance(), Matchers.is(80));

		//Mockito.verify(account,times(0).setBalance(any());
		//Mockito.verify(account,times(0).setBalance(amoutCaptor.capture());
		//assertThat(amountCaptor.getValue(),is(40));
	}
	@Test(expected = CreditNotAuthorizedException.class)
	public void should_throw_exception_when_account_is_null(){

		Account account = Account.builder().balance(70).build();
		accountService.updateMoney(account,-80);
	}

	@Test(expected = BlockedAccountException.class)
	public void should_throw_exception(){
		Account account = Account.builder().balance(50).build();

		when(authorizationService.isAllowed(any())).thenReturn(false);
		accountService.updateMoney(account,50);
	}

}
