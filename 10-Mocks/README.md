Marta Grimaldos López
ER1556

2.1:
InOrder orderedVerification = inOrder(mockDatabase);
orderedVerification.verify(mockDatabase).connect();
orderedVerification.verify(mockDatabase).queryAll();
orderedVerification.verify(mockDatabase).close();

5.1: the sequence in which expectations are defined is important
when(mockDatabase.getExpensesByCategory(anyString())).thenReturn(Collections.emptyList());
when(mockDatabase.getExpensesByCategory("Home")).thenReturn(homeExpenses);
when(mockDatabase.getExpensesByCategory("Car")).thenReturn(carExpenses);
