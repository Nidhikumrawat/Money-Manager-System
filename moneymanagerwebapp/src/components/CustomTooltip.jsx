// CustomTooltip.jsx
import React from 'react';

const CustomTooltip = ({ active, payload, label }) => {
  if (active && payload?.length) {
    const { total, details } = payload[0].payload;

    return (
      <div className="bg-white border border-gray-200 rounded-md shadow-md p-3 text-sm max-w-xs">
        <p className="font-semibold text-gray-800 mb-1">{label}</p>
        <p className="text-green-600 font-medium mb-2">Total: ₹{total.toLocaleString()}</p>
        <ul className="list-disc list-inside text-gray-700">
          {details.map((item, index) => (
            <li key={index}>
              {item.source || "Unknown"}: ₹{item.amount.toLocaleString()}
            </li>
          ))}
        </ul>
      </div>
    );
  }

  return null;
};

export default CustomTooltip;