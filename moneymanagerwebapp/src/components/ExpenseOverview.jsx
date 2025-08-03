import { useEffect, useState } from "react";
import { prepareExpenseLineChartData } from "../util/prepareExpenseLineChartData";
import CustomLineChart from '../components/CustomLineChart.jsx';
import { Plus } from 'lucide-react';

const ExpenseOverview = ({ transactions, onAddExpense }) => {
    const [chartData, setChartData] = useState([]);

    useEffect(() => {
        const result = prepareExpenseLineChartData(transactions || []);
        setChartData(result);
    }, [transactions]);

    return (
        <div className="card mb-6">
            <div className="flex items-center justify-between">
                <div>
                    <h5 className="text-lg">Expense Overview</h5>
                    <p className="text-xs text-gray-400 mt-0.5">
                        Monitor your spending over time and analyze your expense trends.
                    </p>
                </div>
                <button className="add-btn" onClick={onAddExpense}>
                    <Plus size={15} className="text-lg" /> Add Expense
                </button>
            </div>
            <div className="mt-10">
                <CustomLineChart data={chartData} />
            </div>
        </div>
    );
};

export default ExpenseOverview;
