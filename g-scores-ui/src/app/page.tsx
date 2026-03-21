"use client";

import React, { useState, useMemo, useEffect } from "react";
import {
  Sidebar,
  TopBar,
  MobileNav,
} from "@/components/layout/layout-components";
import { GROUP_SUBJECTS, SUBJECT_LABELS } from "@/lib/data";
import { ExamResultResponse } from "@/api/type";
import { ChevronDown } from "lucide-react";
import { cn } from "@/lib/utils";
import { getTop10HighestScores } from "@/api/api";

const SUBJECT_API_KEY: Record<string, keyof ExamResultResponse> = {
  math: "math",
  physics: "physics",
  chemistry: "chemistry",
  biology: "biology",
  literature: "literature",
  english: "foreignLanguage",
  history: "history",
  geography: "geography",
  civics: "civicEducation",
};

function getScore(result: ExamResultResponse, subject: string): number {
  return (result[SUBJECT_API_KEY[subject]] as number) ?? 0;
}

export default function RankingPage() {
  const [selectedGroup, setSelectedGroup] = useState<
    "A00" | "A01" | "B" | "C" | "D01" | "D07"
  >("A00");

  const groupSubjects = GROUP_SUBJECTS[selectedGroup];

  const [top10HighestScores, setTop10HighestScores] = useState<
    ExamResultResponse[]
  >([]);

  useEffect(() => {
    getTop10HighestScores(selectedGroup).then((data) => {
      setTop10HighestScores(data);
    });
  }, [selectedGroup]);

  return (
    <div className="min-h-screen bg-surface">
      <TopBar />
      <Sidebar />

      <main className="lg:ml-64 pt-24 px-4 md:px-10 pb-24 lg:pb-12 min-h-screen">
        {/* Header Section */}
        <header className="mb-10 max-w-5xl">
          <h1 className="text-4xl md:text-5xl font-black text-blue-900 tracking-tight mb-2">
            TOP 10 HIGHEST SCORES
          </h1>
        </header>

        {/* Main Table Section */}
        <div className="max-w-7xl">
          <div className="lg:col-span-3">
            <div className="bg-white rounded-xl shadow-sm overflow-hidden border border-slate-200">
              <div className="p-6 border-b border-slate-100 flex justify-end">
                <div className="relative shrink-0">
                  <select
                    value={selectedGroup}
                    onChange={(e) => setSelectedGroup(e.target.value as any)}
                    className="appearance-none bg-slate-50 border-b-2 border-slate-200 rounded-lg pl-4 pr-10 py-3 text-sm font-semibold focus:outline-none cursor-pointer"
                  >
                    <option value="A00">Khối A00 (Toán, Lý, Hóa)</option>
                    <option value="A01">Khối A01 (Toán, Lý, Anh)</option>
                    <option value="B">Khối B (Toán, Hóa, Sinh)</option>
                    <option value="C">Khối C (Văn, Sử, Địa)</option>
                    <option value="D01">Khối D01 (Toán, Văn, Anh)</option>
                    <option value="D07">Khối D07 (Toán, Hóa, Anh)</option>
                  </select>
                  <ChevronDown className="absolute right-3 top-1/2 -translate-y-1/2 pointer-events-none text-slate-400 w-4 h-4" />
                </div>
              </div>

              <div className="overflow-x-auto">
                <table className="w-full text-left border-collapse">
                  <thead>
                    <tr className="bg-slate-50">
                      <th className="px-6 py-4 text-[10px] font-black uppercase text-slate-500">
                        RANK
                      </th>
                      <th className="px-6 py-4 text-[10px] font-black uppercase text-slate-500">
                        SBD
                      </th>
                      {groupSubjects.map((subject) => (
                        <th
                          key={subject}
                          className="px-6 py-4 text-[10px] font-black uppercase text-slate-500"
                        >
                          {SUBJECT_LABELS[subject]}
                        </th>
                      ))}
                      <th className="px-6 py-4 text-[10px] font-black uppercase text-slate-500">
                        TỔNG
                      </th>
                    </tr>
                  </thead>
                  <tbody className="divide-y divide-slate-100">
                    {top10HighestScores.map((examResult, index) => (
                      <tr
                        key={examResult.sbd}
                        className="hover:bg-slate-50 transition-colors group"
                      >
                        <td className="px-6 py-5">
                          <div
                            className={cn(
                              "w-8 h-8 rounded-full flex items-center justify-center font-black text-sm shadow-sm",
                              index === 0
                                ? "bg-yellow-400 text-yellow-900"
                                : "bg-slate-100 text-slate-600",
                            )}
                          >
                            {index + 1}
                          </div>
                        </td>
                        <td className="px-6 py-5 text-sm font-bold text-slate-500">
                          {examResult.sbd}
                        </td>
                        {groupSubjects.map((subject) => {
                          const score = getScore(examResult, subject);
                          return (
                            <td
                              key={subject}
                              className="px-6 py-5 font-black text-base"
                            >
                              <span className="text-slate-500">{score}</span>
                            </td>
                          );
                        })}
                        <td className="px-6 py-5 font-black text-base">
                          <span className="text-slate-500">
                            {examResult.total}
                          </span>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </main>

      <MobileNav />
    </div>
  );
}
