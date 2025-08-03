import React from "react";
import {
  PieChart,
  Pie,
  Cell,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";

const CustomPieChart = ({ data, label, totalAmount, colors, showTextAnchor }) => (
  <div className="relative w-full max-w-md mx-auto p-6 rounded-xl shadow-lg bg-white dark:bg-gray-900">
    <ResponsiveContainer width="100%" height={250}>
      <PieChart>
        <Pie
          data={data}
          dataKey="amount"
          nameKey="name"
          cx="50%"
          cy="50%"
          innerRadius={60}
          outerRadius={90}
          paddingAngle={2}
          labelLine={false}
          label={showTextAnchor ? ({ name }) => name : false}
        >
          {data.map((entry, index) => (
            <Cell key={`cell-${index}`} fill={colors[index % colors.length]} />
          ))}
        </Pie>
        <Tooltip
          formatter={(value) => [`₹${value.toLocaleString()}`, "Amount"]}
          contentStyle={{
            backgroundColor: "#f3f4f6",
            borderColor: "#d1d5db",
            borderRadius: "8px",
            fontSize: "0.85rem",
          }}
        />
        <Legend verticalAlign="bottom" height={36} wrapperStyle={{ fontSize: "0.8rem" }} />
      </PieChart>
    </ResponsiveContainer>
    {/* Centered total amount */}
    <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 text-center z-10 pointer-events-none">
      <div className="text-sm text-gray-500 font-medium">{label}</div>
      <div className="text-1xl font-bold text-purple-700 dark:text-purple-400">
        ₹{totalAmount.toLocaleString()}
      </div>
    </div>
  </div>
);

export default CustomPieChart;
