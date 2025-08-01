import { Download ,Mail } from "lucide-react"
import TransactionInfoCard from "./TransactionInfoCard";
import moment from "moment";
import '../index.css';

const IncomeList = ({transactions,onDelete,onDownload, onEmail}) =>{
return(
    <div className="card ">
        <div className="flex items-center justify-between ">
            <h5 className="text-lg">Income Sources</h5>
            <div className="flex items-center justify-end gap-2 ">
                <button className="card-btn bg-purple-100 text-purple-700 border border-purple-400 px-4 py-0.5 rounded-lg hover:bg-purple-300 transition-all shadow-md hover:shadow-lg" onClick={onEmail}>
                    <Mail size={15} className="text-base" />Email
                </button>
                <button className="card-btn bg-purple-100 text-purple-700 border border-purple-400 px-4 py-0.5 rounded-lg hover:bg-purple-300 transition-all shadow-md hover:shadow-md " onClick={onDownload}>
                    <Download size={15} className="text-base" />Download
                </button>
            </div>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2">
            {/** Display the incomes */}
            {transactions?.map((income) => (
                <TransactionInfoCard
                    key={income.id}
                    title={income.name}
                    icon={income.icon}
                    date={moment(income.date).format('DD MMM YYYY')}
                    amount={income.amount}
                    type="income"
                    onDelete={()=> onDelete(income.id)}
                />
            ))}
        </div>
    </div>
)
}

export default IncomeList;