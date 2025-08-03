export const prepareExpenseLineChartData = (transactions = []) => {

  const expenseByDate = {};

  transactions.forEach((txn) => {
    // You can uncomment the next line to filter only expense transactions if needed
    // if (txn.type === "expense") {
      const date = new Date(txn.date).toISOString().split("T")[0];

      if (!expenseByDate[date]) {
        expenseByDate[date] = {
          total: 0,
          details: [],
        };
      }

      expenseByDate[date].total += Number(txn.amount);
      expenseByDate[date].details.push(txn);
    // }
  });

  return Object.entries(expenseByDate)
    .map(([date, { total, details }]) => ({
      date,
      total,
      details,
    }))
    .sort((a, b) => new Date(a.date) - new Date(b.date));
};
