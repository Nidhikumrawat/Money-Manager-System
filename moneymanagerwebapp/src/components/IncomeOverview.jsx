import { useEffect, useState } from "react";
import  {prepareIncomeLineChartData}  from "../util/prepareIncomeLineChartData";
import CustomLineChart from '../components/CustomLineChart.jsx';
import { Plus } from 'lucide-react';


  const IncomeOverview = ({ transactions, onAddIncome }) => {
   const [chartData, setChartData] = useState([]);

    useEffect(() => {
        const result = prepareIncomeLineChartData(transactions || []) ;
        console.log( "Result",result);
        setChartData(result);
    }, [transactions]);


//     const dummyTransactions = [
//   { date: "2024-07-01", type: "income", amount: 500 },
//   { date: "2024-07-02", type: "income", amount: 700 },
//   { date: "2024-07-03", type: "income", amount: 300 }
// ];

// useEffect(() => {
//     const result = prepareIncomeLineChartData(dummyTransactions);
//     setChartData(result);
// }, []);

    return (
        <div className="card mb-6">
            <div className="flex items-center justify-between">
                <div>
                    <h5 className="text-lg">Income Overview</h5>
                    <p className="text-xs text-gray-400 mt-0.5">
                        Track your earnings over time and analyze your income trends.
                    </p>
                </div>
                <button className="add-btn" onClick={onAddIncome}>
                    <Plus size={15} className="text-lg "/> Add Income
                </button>
            </div>
            <div className="mt-10">
                <CustomLineChart data={chartData} />
            </div>
        </div>
    )
}

export default IncomeOverview;