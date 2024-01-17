package com.maksyank.finance.financegoal.mapper;

import com.maksyank.finance.financegoal.domain.common.FinanceGoal;
import com.maksyank.finance.financegoal.domain.request.PreparedFinanceGoal;
import com.maksyank.finance.financegoal.domain.response.FinanceGoalResponse;
import com.maksyank.finance.financegoal.domain.response.FinanceGoalViewResponse;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FinanceGoalMapper {

    public static List<FinanceGoalResponse> sourceToResponse(Collection<FinanceGoal> finGoals) {
        return finGoals.stream().map(FinanceGoalMapper::sourceToResponse).collect(Collectors.toList());
    }

    public static FinanceGoalResponse sourceToResponse(FinanceGoal finGoal) {
        return new FinanceGoalResponse(finGoal.getId(), finGoal.getTitle(), finGoal.getState(),
                finGoal.getDescription(), finGoal.getAmount(), finGoal.getTargetAmount(),
                finGoal.getDeadline(), finGoal.getRiskProfile()
        );
    }

    public static List<FinanceGoalViewResponse> sourceToViewResponse(Collection<FinanceGoal> finGoals) {
        return finGoals.stream().map(FinanceGoalMapper::sourceToViewResponse).collect(Collectors.toList());
    }

    public static FinanceGoalViewResponse sourceToViewResponse(FinanceGoal finGoal) {
        return new FinanceGoalViewResponse(finGoal.getId(), finGoal.getTitle(),
                finGoal.getAmount(), finGoal.getTargetAmount()
        );
    }

    // TO DO impl state enum, now it's just mocked
    // TO DO amount from start mocked (from start 0)
    // riskProfile mocked
    // no impl currency
    // lastChange mocked by null
    public static FinanceGoal preparedToSource(PreparedFinanceGoal preparedFinGoal) {
        return new FinanceGoal(preparedFinGoal.getUserId(), preparedFinGoal.getTitle(), "new",
                preparedFinGoal.getCurrency(), preparedFinGoal.getDescription(),
                0, preparedFinGoal.getTargetAmount(), preparedFinGoal.getDeadline(),
                "riskProfile", preparedFinGoal.getCreatedOn(), null, 0)
    }


}
