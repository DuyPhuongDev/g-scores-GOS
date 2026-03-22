"use client";

import React, { useState, useEffect } from "react";
import {
  ExamGroupResponse,
  ExamResultResponse,
  GroupCode,
  GROUP_SUBJECTS,
  Top10HighestScoresResponse,
} from "@/api/type";
import { ChevronDown } from "lucide-react";
import { cn } from "@/lib/utils";
import { getExamGroups, getTop10HighestScores } from "@/api/api";

export default function RankingPage() {
  const [selectedGroupName, setSelectedGroupName] = useState<string>("A00");
  const [examGroups, setExamGroups] = useState<ExamGroupResponse[]>([]);
  const [top10HighestScores, setTop10HighestScores] = useState<
    Top10HighestScoresResponse[]
  >([]);
  const [loadingGroups, setLoadingGroups] = useState(true);
  const [loadingScores, setLoadingScores] = useState(false);

  useEffect(() => {
    setLoadingGroups(true);
    getExamGroups()
      .then((data) => {
        setExamGroups(data);
        if (data.length > 0) setSelectedGroupName(data[0].groupName);
      })
      .finally(() => setLoadingGroups(false));
  }, []);

  useEffect(() => {
    if (!selectedGroupName) return;
    setLoadingScores(true);
    getTop10HighestScores(selectedGroupName)
      .then((data) => setTop10HighestScores(data))
      .finally(() => setLoadingScores(false));
  }, [selectedGroupName]);

  return (
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
                  value={selectedGroupName}
                  onChange={(e) => setSelectedGroupName(e.target.value)}
                  className="appearance-none bg-slate-50 border-b-2 border-slate-200 rounded-lg pl-4 pr-10 py-3 text-sm font-semibold focus:outline-none cursor-pointer"
                >
                  {examGroups.map((group) => (
                    <option key={group.id} value={group.groupName}>
                      {group.groupName +
                        " (" +
                        GROUP_SUBJECTS[group.groupName as GroupCode]?.join(
                          ", ",
                        ) +
                        ")"}
                    </option>
                  ))}
                </select>
                <ChevronDown className="absolute right-3 top-1/2 -translate-y-1/2 pointer-events-none text-slate-400 w-4 h-4" />
              </div>
            </div>

            <div className="overflow-x-auto relative">
              {loadingScores && (
                <div className="absolute inset-0 bg-white/70 flex items-center justify-center z-10">
                  <div className="w-6 h-6 border-2 border-blue-900 border-t-transparent rounded-full animate-spin" />
                </div>
              )}
              {loadingGroups ? (
                <div className="flex items-center justify-center py-20">
                  <div className="w-6 h-6 border-2 border-blue-900 border-t-transparent rounded-full animate-spin" />
                </div>
              ) : (
              <table className="w-full text-left border-collapse">
                <thead>
                  <tr className="bg-slate-50">
                    <th className="px-6 py-4 text-[14px] font-black uppercase text-slate-500">
                      RANK
                    </th>
                    <th className="px-6 py-4 text-[14px] font-black uppercase text-slate-500">
                      SBD
                    </th>
                    {examGroups
                      .find((group) => group.groupName === selectedGroupName)
                      ?.subjects.map((subject) => (
                        <th
                          key={subject.id}
                          className="px-6 py-4 text-[14px] font-black uppercase text-slate-500"
                        >
                          {subject.subjectName}
                        </th>
                      ))}
                    <th className="px-6 py-4 text-[14px] font-black uppercase text-slate-500">
                      TỔNG
                    </th>
                  </tr>
                </thead>
                <tbody className="divide-y divide-slate-100">
                  {top10HighestScores.map((top10HighestScore, index) => (
                    <tr
                      key={top10HighestScore.sbd}
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
                        {top10HighestScore.sbd}
                      </td>
                      {examGroups
                        .find((group) => group.groupName === selectedGroupName)
                        ?.subjects.map((subject) => {
                          const score =
                            top10HighestScore.scores.find(
                              (score) => score.id === subject.id,
                            )?.score ?? 0;
                          return (
                            <td key={subject.id} className="px-6 py-5">
                              <span className="text-slate-500">{score}</span>
                            </td>
                          );
                        })}
                      <td className="px-6 py-5">
                        <span className="text-slate-500">
                          {top10HighestScore.total}
                        </span>
                      </td>
                    </tr>
                  ))}
                </tbody>
                </table>
              )}
            </div>
          </div>
        </div>
      </div>
    </main>
  );
}
