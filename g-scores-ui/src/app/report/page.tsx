"use client";

import React, { useEffect, useState } from "react";
import { StackBarChart } from "./components/StackBarChart";
import { getChartData } from "@/api/api";
import { ChartDataResponse } from "@/api/type";

export default function ReportsPage() {
  const [chartData, setChartData] = useState<ChartDataResponse[]>([]);

  useEffect(() => {
    getChartData().then((data) => setChartData(data));
  }, []);

  return (
    <main className="lg:ml-64 pt-24 px-6 pb-24 lg:pb-12">
      <div className="max-w-6xl mx-auto">
        {/* Header Section */}
        <div className="flex flex-col md:flex-row md:items-end justify-between mb-10 gap-4">
          <div>
            <h1 className="text-4xl font-bold tracking-tight text-blue-900">
              Report Analysis
            </h1>
          </div>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-1 gap-6">
          <div className="bg-white p-8 rounded-xl shadow-sm border border-slate-200">
            <div className="flex justify-between items-center mb-10">
              <h3 className="text-xl font-bold text-blue-900">
                Subject Distribution Chart
              </h3>
              <div className="hidden sm:flex gap-4">
                <div className="flex items-center gap-2">
                  <div className="w-3 h-3 rounded-full bg-blue-900"></div>
                  <span className="text-[10px] font-bold uppercase text-slate-500 tracking-wider">
                    &lt; 4
                  </span>
                </div>
                <div className="flex items-center gap-2">
                  <div className="w-3 h-3 rounded-full bg-yellow-400"></div>
                  <span className="text-[10px] font-bold uppercase text-slate-500 tracking-wider">
                    4 - 6
                  </span>
                </div>
                <div className="flex items-center gap-2">
                  <div className="w-3 h-3 rounded-full bg-slate-300"></div>
                  <span className="text-[10px] font-bold uppercase text-slate-500 tracking-wider">
                    6 - 8
                  </span>
                </div>
                <div className="flex items-center gap-2">
                  <div className="w-3 h-3 rounded-full bg-red-300"></div>
                  <span className="text-[10px] font-bold uppercase text-slate-500 tracking-wider">
                    &gt;= 8
                  </span>
                </div>
              </div>
            </div>
            <StackBarChart chartData={chartData} />
          </div>
        </div>
      </div>
    </main>
  );
}
