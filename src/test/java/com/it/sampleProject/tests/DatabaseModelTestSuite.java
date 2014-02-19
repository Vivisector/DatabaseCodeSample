package com.it.sampleProject.tests;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.it.sampleProject.database.model.DocumentTest;
import com.it.sampleProject.database.model.EventTest;
import com.it.sampleProject.database.model.ExchangeTest;

@RunWith(Categories.class)
@SuiteClasses( { DocumentTest.class, EventTest.class, ExchangeTest.class })
public class DatabaseModelTestSuite {}