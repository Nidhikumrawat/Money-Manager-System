
export  const prepareIncomeLineChartData = (transactions = []) => {

    //console.log("transactions input:", transactions); 
  const incomeByDate = {};

  transactions.forEach((txn) => {
  // if (txn.type === "income") {
          const date = new Date(txn.date).toISOString().split('T')[0];

      if (!incomeByDate[date]) {
        incomeByDate[date] = {
          total: 0,
          details: []
        };
      }

      incomeByDate[date].total += Number(txn.amount);
      incomeByDate[date].details.push(txn);
  //  }
  });

  return Object.entries(incomeByDate).map(([date, { total, details }]) => ({
    date,
    total,
    details
  }))
   .sort((a, b) => new Date(a.date) - new Date(b.date));
};




