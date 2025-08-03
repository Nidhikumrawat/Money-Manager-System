// components/CustomLineChart.jsx
import React from "react";
import {
  LineChart, Line, XAxis, YAxis, Tooltip, ResponsiveContainer, CartesianGrid
} from "recharts";


const CustomTooltip = ({ active, payload, label }) => {
  if (active && payload?.length) {
    const details = payload[0].payload.details;
    return (
      <div className="bg-white p-2 rounded shadow text-sm">
        <p className="font-semibold">{label}</p>
        <p>Total: ₹{payload[0].value.toLocaleString()}</p>
        <ul>
          {details.map((item, index) => (
            <li key={index}>{item.source || item.source}: ₹{item.amount.toLocaleString()}</li>
          ))}
        </ul>
      </div>
    );
  }
  return null;
};

const CustomLineChart = ({ data }) => {
  return (
    <div className="w-full h-65">
      <ResponsiveContainer width="100%" height="100%">
        <LineChart
          data={data}
          width={500}
          height={400}
          margin={{ top: 10, right: 30, left: 0, bottom: 0 }}
        >
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="date" />
          <YAxis />
          <Tooltip content={<CustomTooltip />} />
          <Line
            type="monotone"
            dataKey="total"
            stroke="green"
            strokeWidth={2}
            dot={{ r: 4 }}
            activeDot={{ r: 6 }}
          />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
};

export default CustomLineChart;


