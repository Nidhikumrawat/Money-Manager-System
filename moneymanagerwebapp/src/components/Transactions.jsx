import { ArrowRight } from "lucide-react";
import TransactionInfoCard from "./TransactionInfoCard";
import moment from "moment";

const Transactions = ({transactions,onMore, title}) =>{
    return(
<div className="card">
    <div className="flex items-center justify-between">
        <h5 className="text-lg">{title}</h5>
        <button className="card-btn" onClick={onMore}>
            More <ArrowRight className="text-base" size={15}/>
        </button>
    </div>
    <div className="div mt-6">
        {transactions?.slice(0,5)?.map(item => (
            <TransactionInfoCard
            key={item.id}
            title={item.name}
            icon={item.icon}
            date={moment(item.data).format("Do MMM YYYY")}
            amount={item.amount}
            type={item.type}
            hideDeleteBtn
            />
        ))}
    </div>
</div>
    )
}

export default Transactions;